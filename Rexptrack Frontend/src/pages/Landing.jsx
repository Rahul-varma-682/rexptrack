import { Link } from 'react-router-dom'

const features = [
  ['◉', 'Smart finances', 'Track every rupee, set budgets, and watch your savings grow.', 'bg-primary-light text-primary'],
  ['✦', 'AI companion', 'Gentle, helpful nudges built around your unique habits.', 'bg-[#EEEDFE] text-[#534AB7]'],
  ['♡', 'Wellness aware', 'See how life habits connect to your wellbeing and goals.', 'bg-[#FAEEDA] text-[#854F0B]'],
  ['◎', 'Goal tracking', 'Set meaningful goals and celebrate every small win.', 'bg-[#FAECE7] text-[#993C1D]'],
  ['◌', 'Smart reminders', 'Never forget bills, milestones, health, or family.', 'bg-primary-light text-primary'],
  ['◔', 'Rich analytics', 'Beautiful clarity on where your time and money go.', 'bg-[#EEEDFE] text-[#534AB7]']
]

export default function Landing() {
  return <main className="min-h-screen bg-sand">
    <nav className="mx-auto flex max-w-7xl items-center justify-between border-b border-line px-5 py-4 sm:px-8">
      <Link to="/" className="flex items-center gap-2 font-bold"><span className="brand-mark">↗</span>RexpTrack</Link>
      <div className="hidden gap-7 text-sm text-muted md:flex"><a href="#features">Features</a><a href="#finance">Finance</a><a href="#ai">AI</a><a href="#pricing">Pricing</a></div>
      <div className="flex items-center gap-3"><Link to="/login" className="text-sm font-medium text-primary">Sign in</Link><Link to="/register" className="primary-button text-sm">Get started</Link></div>
    </nav>
    <section className="mx-auto max-w-4xl px-5 py-20 text-center sm:py-28">
      <span className="inline-flex rounded-full bg-primary-light px-3 py-1 text-xs font-semibold text-primary">✦ AI-powered life management</span>
      <h1 className="mt-5 text-4xl font-bold leading-tight tracking-tight text-ink sm:text-6xl">Your life, <span className="text-primary">finally under control</span></h1>
      <p className="mx-auto mt-5 max-w-2xl text-base leading-7 text-muted">Track expenses, manage goals, monitor wellness, and let your personal AI companion guide you — all in one calm, beautiful place.</p>
      <div className="mt-8 flex justify-center gap-3"><Link to="/register" className="primary-button">Start for free</Link><Link to="/login" className="rounded-lg border border-primary px-4 py-2.5 font-semibold text-primary">Sign in</Link></div>
    </section>
    <section id="features" className="mx-auto grid max-w-6xl grid-cols-1 gap-4 px-5 pb-16 sm:grid-cols-2 lg:grid-cols-3">{features.map(([icon, title, text, color]) => <article key={title} className="card p-6"><span className={`grid h-10 w-10 place-items-center rounded-lg text-lg ${color}`}>{icon}</span><h2 className="mt-4 font-bold">{title}</h2><p className="mt-2 text-sm leading-6 text-muted">{text}</p></article>)}</section>
    <section className="border-y border-line bg-white"><div className="mx-auto grid max-w-5xl grid-cols-2 gap-8 px-5 py-9 text-center sm:grid-cols-4">{[['50K+', 'Active users'], ['₹2.4Cr', 'Tracked monthly'], ['4.9★', 'User rating'], ['₹299/mo', 'Premium plan']].map(([value, label]) => <div key={label}><div className="text-2xl font-bold text-primary">{value}</div><div className="mt-1 text-sm text-muted">{label}</div></div>)}</div></section>
    <section className="mx-auto my-16 flex max-w-6xl flex-col gap-6 rounded-2xl bg-primary p-7 sm:flex-row sm:items-center sm:justify-between"><div><h2 className="text-xl font-bold text-white">Ready to feel in control?</h2><p className="mt-1 text-sm text-[#9FE1CB]">Free forever for basics. Upgrade anytime.</p></div><Link to="/register" className="rounded-lg bg-white px-5 py-2.5 text-center font-semibold text-primary">Create free account</Link></section>
    <footer className="border-t border-line px-5 py-8 text-center text-sm text-muted">© {new Date().getFullYear()} RexpTrack. Your life, clearly understood.</footer>
  </main>
}
